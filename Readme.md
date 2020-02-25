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

- AWS:
-- a valid certificate for the domain
-- a VPC, private and public subnets

Decisions:
- use ECS, to containerize the application
- cloudformation: just base infrastructure; deployment is done via https://github.com/silinternational/ecs-deploy

Issues:
- DNS + HTTPS certificate: I need a real domain to get a real certificate; since I lack this, I'm using a self signed certificate

Things not done or badly done, to save time:
- quite a bit of hardcoding:
-- Clouformation templates could use more parameters, and ansible to provide them)
-- CFN parameters hardcoded - ansible is meant to retrieve them via lookup or parameters
- Barebone infrastructure: not much thought given to logs, alerts ...etc
- The "bootstrap" of the pipeline is not automated - it is left as is on purpose, as automating it can be dangerous (ie: force pushing an image as latest)

CURL:
curl -v -H 'content-type: application/json' -X POST --data '{"farmId":"abc","startTime":"timehere","duration":"30","status":"Requested"}' 'https://uat-1661608408.ap-southeast-2.elb.amazonaws.com/orders/' -k