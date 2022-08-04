#!/bin/sh

echo
echo "###### BUILD FRONTEND ######"
echo

echo ">> sudo -i"
sudo -i

echo ">> cd /jenkins/workspace/frontend/"
cd /jenkins/workspace/frontend/

echo ">> sudo rm -rf node_modules"
sudo rm -rf node_modules

echo ">> sudo npm i --save-exact"
sudo npm i --save-exact

echo ">> sudo npm run build"
sudo npm run build

echo
echo "###### FE DONE ######"
echo

echo "###### DEPLOY BACKEND ######"
echo

echo ">> sudo kill -9 `ps -ef | grep '/jenkins/workspace/mungtopia/backend/build/libs/mungtopia-0.0.1-SNAPSHOT.jar'|awk '{print $2}'`"
sudo kill -9 `ps -ef | grep '/jenkins/workspace/mungtopia/backend/build/libs/mungtopia-0.0.1-SNAPSHOT.jar'|awk '{print $2}'`

echo ">> nohup java -jar /jenkins/workspace/mungtopia/backend/build/libs/mungtopia-0.0.1-SNAPSHOT.jar > /dev/null 2>/jenkins/workspace/springboot.log &"
nohup java -jar /jenkins/workspace/mungtopia/backend/build/libs/mungtopia-0.0.1-SNAPSHOT.jar > /dev/null 2>/jenkins/workspace/springboot.log &

echo
echo "###### BE DONE ######"
echo
