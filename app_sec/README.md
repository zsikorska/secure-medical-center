# secure app
Use Intellij Ultimate most preferably

# how to run
```
cd app_sec
make all
```
Alternative run command:
```
docker-compose up
```
If there are some issues with versions of the docker app, add `--build` run parameter
```
docker-compose up --build
```
The web-app is now located under http://localhost

## handling secrets
`.env` file holds some secret environment variables. Our solution assumes that the application code is be hardcoded credentials free. The credentials securing strategy is to pass them during the application run-time, which the deployment management system would handle. They mostly supply the secrets manager solutions that are safe, so the code with the repository itself would be credentials-free. Normally, the `.env` file should not be pushed into this repository - it is just for explaining purposes and easier assessment.
