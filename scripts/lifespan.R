#!/usr/bin/env Rscript
# R script calculating the number of days elapsed since your birth (or any date)

by=1994 # birth year
bm=3 # birth month
bd=10 # birth day

cy=as.integer(format(Sys.Date(), format="%Y")) # current year
cm=as.integer(format(Sys.Date(), format="%m")) # current month
cd=as.integer(format(Sys.Date(), format="%d")) # current day

yl=by:cy # years lived 
lyl=c(yl[yl %% 400 == 0], intersect(yl[yl %% 100 != 0], yl[yl %% 4 == 0])) # leap years lived
#     ^first condtion     ^second condition 
#         divisible by 400     divisible by 4 and are not divisible by 100
nyl=setdiff(yl,lyl) # normal years lived

dim=c(31,28,31,30,31,30,31,31,30,31,30,31) # (normal year) days in month
ldim=dim; ldim[2]=dim[2]+1 # (leap year) days in month 

if (by == lyl[1]) { # if you were born in leap year 
    diy=sum(ldim[bm:12])-bd # number days in the first year of your life
} else {
    diy=sum(dim[bm:12])-bd # number days in the first year of your life
}

diy[2]=sum(ldim)*length(setdiff(lyl, c(by, cy)))+sum(dim)*length(setdiff(nyl, c(by, cy))) # number of all the days you've lived not counting year you were born and the current year

if (cy == lyl[length(lyl)]) { # if current year is leap year
    diy[3]=sum(ldim[1:cm])-(dim[cm]-cd) # number days in the currrent year of your life
} else {
    diy[3]=sum(dim[1:cm])-(dim[cm]-cd) # number days in the current year of your life
}

sum(diy, 1) # +1 to count the day you live today

