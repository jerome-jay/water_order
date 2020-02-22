Notes:
- Jenkins server:
-- python-pip
-- pip install ansible boto3 botocore
-- docker
-- corretto 11
-- jq
-- aws cli (already installed in amazon ami)

- Jenkins plugins: 
-- pipeline
-- github

Decisions:
- use ECS, to containerize the application
- cloudformation: just base infrastructure; deployment is done via https://github.com/silinternational/ecs-deploy

Issues:
- DNS + HTTPS: need a real domain to get a real certificate ...