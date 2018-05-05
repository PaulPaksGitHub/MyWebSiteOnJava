#!/bin/bash

testValue=30 #общее количество тестов
ok=0 #количестуо успешных тестов

#My tests
java -cp "./libraries/*:app.jar" com.company.Main -egg foo
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login pa -pass 12
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'pa' -pass '12' -res 'A' -role 'READ'
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login pa -pass 12 -res A.B -role READ -ds 2101-12-12 -de 1233-12-03 -vol 4
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login pa
if [ $? -eq 2 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login pa -pass 12 -role READ
if [ $? -eq 4 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login kukuruzka -pass 12
if [ $? -eq 1 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login pa -pass 34
if [ $? -eq 2 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login pa -pass 12 -res GG -role READ
if [ $? -eq 4 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login pa -pass 12 -res A.B -role SORRY
if [ $? -eq 3 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login pa -pass 12 -res A.B -role READ -ds 12-2101-12 -de 1233-12-03 -vol 4
if [ $? -eq 5 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login pa -login pa -pass 12 -res A.B -role READ -ds 2101-12-12 -de 1233-12-03 -vol 4.7
if [ $? -eq 5 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -vol 4 -login pa -role READ -ds 2101-12-12 -pass 12 -res A.B -de 1233-12-03
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi


#C tests
java -cp "./libraries/*:app.jar" com.company.Main
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -h
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'XXX' -pass 'XXX'
if [ $? -eq 1 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'XXX'
if [ $? -eq 2 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ'
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a'
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b'
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'XXX' -res 'a.b'
if [ $? -eq 3 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'XXX'
if [ $? -eq 4 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'WRITE' -res 'a'
if [ $? -eq 4 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'WRITE' -res 'a.bc'
if [ $? -eq 4 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '2015-01-01' -de '2015-12-31' -vol '100'
if [ $? -eq 0 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '01-01-2015' -de '2015-12-31' -vol '100'
if [ $? -eq 5 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'jdoe' -pass 'sup3rpaZZ' -role 'READ' -res 'a.b' -ds '2015-01-01' -de '2015-12-31' -vol 'XXX'
if [ $? -eq 5 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'X' -pass 'X' -role 'READ' -res 'X' -ds '2015-01-01' -de '2015-12-31' -vol 'XXX'
if [ $? -eq 1 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
fi

java -cp "./libraries/*:app.jar" com.company.Main -login 'X' -pass 'X' -role 'READ' -res 'X'
if [ $? -eq 1 ]
then
    echo "ok"
    ok=$(($ok + 1))
else
    echo "CRASH"
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


