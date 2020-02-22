Notes:
- Jenkins server needs:
-- python-pip
-- pip install ansible boto3 botocore
- jenkins plugins: pipeline, github

Decisions:
- use ECS, to containerize the application
- cloudformation: just base infrastructure

Issues:
- DNS + HTTPS: need a real domain to get a real certificate ...