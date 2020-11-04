#!/bin/sh

editor="st -e vim"

excluded_ext=".swp" # seperate with |
dir="/home/user"

prompt_str="Search $dir"
str="$(echo "$prompt_str" | dmenu -i -l 0 -p "$prompt_str")"

limit=100

if test "$str" != "$prompt_str" && test -n "$str"; then
	fn="$(locate -i -l "$limit" "$dir/*$str*" | sed -e "#$dir#~#" -e "s#$excluded_ext\$##" | dmenu -p "Pick a file to edit")"

	if test -n "$fn"; then
		$editor "$fn"
	fi
fi

