DEPLOY_USERNAME=admin
DEPLOY_PASSWORD=admin
mvn tomcat7:redeploy -Dtomcat.username=${DEPLOY_USERNAME} -Dtomcat.password=${DEPLOY_PASSWORD}
