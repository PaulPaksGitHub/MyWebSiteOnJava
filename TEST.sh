#!/bin/bash
#перввым параметром передавать ожидаемый код возврата, вторым параметром - параметры для теста
function test(){
param=$2
code=$1
echo $param
java -cp "commons-cli-1.4.jar;h2-1.4.197.jar;flyway-core-5.0.7.jar" -jar my.jar "$param"
if [ $? -eq $code ]
then
    echo "ok"
    return 0
else
    echo "CRASH"
    return 1
fi
}

testValue=30 #общее количество тестов
ok=0 #количестуо успешных тестов

#My tests
test 0 '-egg foo'
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 ''
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 '-login pa -pass 12'
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 "-login 'pa' -pass '12' -res 'A' -role 'READ' "
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 "-login pa -pass 12 -res A.B -role READ -ds 2101-12-12 -de 1233-12-03 -vol 4"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 2 "-login pa "
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 4 "-login pa -pass 12 -role READ"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 1 "-login kukuruzka -pass 12"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 2 "-login pa -pass 34"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 4 "-login pa -pass 12 -res GG -role READ"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 3 "-login pa -pass 12 -res A.B -role SORRY"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 5 "-login pa -pass 12 -res A.B -role READ -ds 12-2101-12 -de 1233-12-03 -vol 4"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 5 "-login pa -pass 12 -res A.B -role READ -ds 2101-12-12 -de 1233-12-03 -vol 4.7 "
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 "-vol 4 -login pa -role READ -ds 2101-12-12 -pass 12 -res A.B -de 1233-12-03 "
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi


#C tests
test 0 ''
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 "-h"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 1 "-login 'XXX' -pass 'XXX'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 2 "-login 'jdoe' -pass 'XXX'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 "-login 'jdoe' -pass 'sup3rpaZZ'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 3 "-login 'jdoe' -pass 'sup3rpaZZ' -role 'XXX' -res 'a.b'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 4 "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'XXX'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 4 "-login 'jdoe' -pass 'sup3rpaZZ' -role 'WRITE' -res 'a'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 4 "-login 'jdoe' -pass 'sup3rpaZZ' -role 'WRITE' -res 'a.bc'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 0 "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '2015-01-01' -de '2015-12-31' -vol '100'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 5 "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '01-01-2015' -de '2015-12-31' -vol '100' "
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 5 "-login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '2015-01-01' -de '2015-12-31' -vol 'XXX' "
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 1 "-login 'X' -pass 'X' -role 'READ' -res 'X' -ds '2015-01-01' -de '2015-12-31' -vol 'XXX'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

test 1 "-login 'X' -pass 'X' -role 'READ' -res 'X'"
if [ $? -eq 0 ]
then ok=$(($ok + 1))
fi

#Определение количества выполненных тестов
if [ $testValue -eq $ok ]
then
    echo "All tests successful complete"
    exit 0
else
    echo "Complete $ok test(s) from $testValue"
    exit 1
fi


