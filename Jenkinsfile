  node('maven') {
             // define commands
             def mvnCmd = "mvn -s conf/settings.xml"

             stage ('Build') {
               git 'https://github.com/araghunatha2/hello-world-war.git'
               sh "${mvnCmd} clean install -DskipTests=true"
             }
             stage ('Test and Analysis') {
               parallel (
                   'Test': {
                       //sh "${mvnCmd} test"
                       //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
                       echo "dummy Test message"
                   },
                   'Static Analysis': {
                       //sh "${mvnCmd} jacoco:report sonar:sonar -Dsonar.host.url=http://sonarqube:9000 -DskipTests=true"
                       echo "dummy static analysis"
                   }
               )
               
                stage ('Push to Nexus') {
                 //sh "${mvnCmd} deploy -DskipTests=true"
                 echo "dummy push to nexus"
             }

             stage ('Deploy DEV') {
               sh "rm -rf oc-build && mkdir -p oc-build/deployments"
               sh "cp target/helloworld.war oc-build/deployments/ROOT.war"
               sh "oc project "
               // clean up. keep the image stream
               sh "oc delete bc,dc,svc,route -l app=helloworld -n javaweb"
               // create build. override the exit code since it complains about exising imagestream
               sh "oc new-build --name=helloworld --image-stream=jboss-eap70-openshift --binary=true --labels=app=helloworld -n javaweb || true"
               // build image
               sh "oc start-build helloworld --from-dir=oc-build --wait=true -n javaweb"
               // deploy image
               sh "oc new-app helloworld:latest -n javaweb"
               sh "oc expose svc/helloworld -n javaweb"
             }
             }
 
