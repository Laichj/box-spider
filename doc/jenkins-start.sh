#!/usr/bin/env bash
source /etc/profile
# 项目部署路径
project_dir=/opt/apps/jweb/box-spider
# 项目名称 模糊匹配
app_name=box-spider
# 配置环境
env_profile=dev


function log(){
  echo "[ `date '+%Y-%m-%d %H:%M:%S'` ] $1"
}

function check(){
    log "check whether ${app_name} is running";
    tpid=`ps -ef|grep ${app_name}|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ "${tpid}" ]; then
        log "${app_name}  is running!"
    else
        log "${app_name} is not running!"
    fi
}

function start(){
    # 获取jar
    jar_file_name=`ls ${DIR}| awk '/^.*jar$/{print $app_name}'`
    log "Trying start ${jar_file_name}";
    nohup java  -Xmx2g -Xmn800m -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError \
    -jar ${project_dir}/${jar_file_name} --spring.profiles.active=${env_profile} --author=aaron  > /dev/null 2>&1 &
    log "Start success!"
}

function stop(){
    log "Trying to stop ${app_name}";

    tpid=`ps -ef|grep ${app_name}|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ "${tpid}" ]; then
        log 'Stop Process...'
        kill -15 $tpid
    fi
    sleep 5
    tpid=`ps -ef|grep ${app_name}|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ "${tpid}" ]; then
        log 'Kill Process!'
        kill -9 $tpid
    else
        log 'Stop Success!'
    fi
}

function print_usage(){
  log "    Usage: sh jenkins-start-prod.sh <param>"
  log "    the valid params are ： check, start, stop, restart "
}



  case $1 in
    start)
       start
       ;;
    check)
       check
       ;;
    stop)
       stop
       ;;
    restart)
       stop
       start
       ;;
    *)
       print_usage
       ;;
   esac
