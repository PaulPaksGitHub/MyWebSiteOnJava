#!/bin/bash
#перввым параметром передавать ожидаемый код возврата, вторым параметром - параметры для теста
function test(){
param=$2
code=$1
echo $param
java -jar my.jar "$param"
if [ $? -eq $code ]
then
echo "ok"
return 0
else
echo "CRASH"
return 1
fi
}
testValue=2
ok=0

test 0 '-login pa -pass 12'
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi
echo $ok

test 2 '-login pa -pass 43'
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi
echo $ok

if [ $testValue -eq $ok ]
then
echo "All test successful complete"
else
echo "Complete $ok tests from $testValue"
fi

sleep 10
