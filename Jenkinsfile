node('maven') {
             // define commands
             //def mvnCmd = "mvn -s conf/settings.xml"
             def mvnCmd = "mvn "

             stage ('Build') {
               git 'https://github.com/araghunatha2/hello-world-war.git'
               sh "${mvnCmd} clean install -DskipTests=true"
             }
             
                stage ('Push to Nexus') {
                 //sh "${mvnCmd} deploy -DskipTests=true"
                 echo "dummy push to nexus"
             }

             stage ('Deploy DEV') {
               sh "rm -rf oc-build && mkdir -p oc-build/deployments"
               sh "cp target/helloworldwar.war oc-build/deployments/ROOT.war"
               sh "oc project "
               // clean up. keep the image stream
               sh "oc delete bc,dc,svc,route -l app=helloworld -n javahelloworldweb"
               // create build. override the exit code since it complains about exising imagestream
               sh "oc new-build --name=helloworld --image-stream=jboss-webserver30-tomcat8-openshift --binary=true --labels=app=helloworld -n javahelloworldweb || true"
               // build image
               sh "oc start-build helloworld --from-dir=oc-build --wait=true -n javahelloworldweb"
               // deploy image
               sh "oc new-app helloworld:latest -n javahelloworldweb"
               sh "oc expose svc/helloworld -n javahelloworldweb"
             }
  
            stage ('Deploy Test') {
               timeout(time:5, unit:'MINUTES') {
                  input message: "Promote to STAGE?", ok: "Promote"
               }
              sh "oc scale dc/helloworld --replicas=0"
               def v = version()
               // tag for stage
               sh "oc tag javahelloworldweb/helloworld:latest javahelloworldweb/helloworld:${v}"
               sh "oc project javahelloworldweb"
               // clean up. keep the imagestream
               sh "oc delete bc,dc,svc,route -l app=testhelloworld -n javahelloworldweb"
               // deploy stage image
               sh "oc new-app testhelloworld:${v} -n javahelloworldweb"
               sh "oc expose svc/testhelloworld -n javahelloworldweb"
             }
    def version() {
            def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
            matcher ? matcher[0][1] : null
             }
