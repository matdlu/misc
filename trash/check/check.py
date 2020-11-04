# this script can be used for checking programs with inputs and outputs where one line of the input matches exactly one line of the output
# convention:
# if there is .in file and no .out it means that the output is not supposed to be checked with check.py (but manually)^
import sys
import subprocess

src_ext=".cpp"
exe_ext=".exe"
comp = "g++"

usage = "test.sh filename - file name of a program without the file extension"

if len(sys.argv) != 2:
    print("Wrong number of arguments, should be only one.")
    print(usage)
    sys.exit()

fn = tuple(sys.argv)[1]

comp_src_fn = fn + src_ext
comp_out_fn = fn + exe_ext
comp_cmd =  comp + " -o " + comp_out_fn + " " + comp_src_fn

inmem = open(fn+".in").read().splitlines() # test input file to memory
outmem = open(fn+".out").read().splitlines() # test output file to memory

subprocess.run(comp_cmd) # recompile

for i in range(0, len(inmem)):
    testinput = inmem[i].encode('utf-8')
    programout = subprocess.run(comp_out_fn, input=testinput, stdout=subprocess.PIPE).stdout.decode('utf-8').rstrip()
    if programout == outmem[i]:
        result = "OK"
    else:
        result = "FAIL, should be: " + outmem[i]
    print(result + " " + programout)




