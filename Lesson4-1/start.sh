#!/usr/bin/env bash

MEMORY="-Xms256m -Xmx256m -XX:MaxMetaspaceSize=256m"
GC="-XX:+UseSerialGC"
GC_LOG=" -verbose:gc -Xloggc:./logs/gc_pid_%p.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"
DUMP="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./dumps/"


java $MEMORY $GC $GC_LOG $DUMP -jar target/MemoryKiller.jar > jvm.out

