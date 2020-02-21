Notes:
- Jenkins server needs ansible installed
- jenkins plugins: pipeline, github


Decisions:
- use ECS, to containerize the application
- cloudformation: just base infrastructure

Issues:
- DNS + HTTPS: need a real domain to get a real certificate ...