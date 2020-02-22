Pre-Requisites:
- Jenkins server from Amazon AMI 2:
-- python-pip
-- pip install ansible boto3 botocore
-- docker
-- jq

- Jenkins plugins: 
-- pipeline
-- github

- Jenkins tools:
-- tool named CORRETTO_11, with JAVA_HOME /usr/lib/jvm/java-11-amazon-corretto.x86_64/
-- maven tool named Maven 3.6.3, with maven 3.6.3 version

Decisions:
- use ECS, to containerize the application
- cloudformation: just base infrastructure; deployment is done via https://github.com/silinternational/ecs-deploy

Issues:
- DNS + HTTPS: need a real domain to get a real certificate ...