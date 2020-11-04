#!/bin/sh

pkgs_with_version="$(xbps-query -l | grep ii | cut -d' ' -f2 | sed '/-devel/d')"
version="$(echo "$pkgs_with_version" | rev | cut -d'-' -f1 | rev)"

sed_cmds=""
for version in $version; do
	sed_cmds="-e s/-$version/-devel/ $sed_cmds"
done

potential_pkgs_devel="$(echo "$pkgs_with_version" | eval "sed $sed_cmds")"

pkgs_devel=""
for potential_devel_pkg in $potential_pkgs_devel; do
	xbps-query -Rs "$potential_devel_pkg" | grep "$potential_devel_pkg" | head -n 1 | cut -d' ' -f2
done

