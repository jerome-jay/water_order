Notes:
- Jenkins server:
-- python-pip
-- pip install ansible boto3 botocore
-- docker.io
-- corretto 11
-- jq
- Jenkins plugins: 
-- pipeline
-- github

Decisions:
- use ECS, to containerize the application
- cloudformation: just base infrastructure

Issues:
- DNS + HTTPS: need a real domain to get a real certificate ...