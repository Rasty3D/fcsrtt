#!/bin/bash

#-------------------------------------------------------------------------------
#
# Script to launch cmake with automatic options
#
# Author: Rasty3D
# email : Rasty3D@gmail.com
# Date  : 06-Feb-2013
#
#-------------------------------------------------------------------------------

# Calculate the SL root folder and pass it as an option to CMake
ROOT_DIR=$(pwd)/$(dirname $0)/..

# Clean SL root path
OLDIFS="$IFS"
IFS='/'
END=false
while ! $END; do
  FOUND_BACK=false
  AUX=""
  ROOT_DIR_AUX=""
  for TOKEN in $ROOT_DIR; do
    if $FOUND_BACK; then
      ROOT_DIR_AUX=${ROOT_DIR_AUX}/${TOKEN}
    else
      if [ "$TOKEN" == ".." ]; then
        FOUND_BACK=true
      else
        if [ -n "$ROOT_DIR_AUX" ]; then
          ROOT_DIR_AUX=${ROOT_DIR_AUX}/${AUX}
        else
          ROOT_DIR_AUX=${AUX}
        fi
        AUX=$TOKEN
      fi
    fi
  done
  if ! $FOUND_BACK; then
    END=true
  else
    ROOT_DIR="/${ROOT_DIR_AUX}"
  fi
done
IFS="$OLDIFS"
OPTIONS="-DROOT_DIR=$ROOT_DIR"


# Launch cmake with the automatic options
echo "Launching CMake:"
echo "  cmake $ROOT_DIR $OPTIONS"
cmake $ROOT_DIR $OPTIONS

