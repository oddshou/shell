#!/bin/bash
function ergodic(){
for file in ` ls $1`
do
	if [ -d $1"/"$file ] #如果 file存在且是一个目录则为真
	then
		ergodic $1"/"$file

	else
		local path=$1"/"$file #得到文件的完整的目录
		local name=$file       #得到文件的名字
        local parentdir=`dirname $1`
        if [[ "$file" == *.png ]] && [[ "$1" == *.imageset ]]
        then
            mv $1"/"$file $parentdir
        fi
	fi

done
if [[ "$1" == *.imageset ]]
then
#删除不需要的文件夹
    echo "rm -r $1"
    rm -r $1
fi

}
INIT_PATH="/Users/houlinhui/Desktop/Images.xcassets"
ergodic $INIT_PATH 
