# Stage 1
FROM node:19-alpine3.15 as build
WORKDIR /app

COPY package.json package-lock.json ./
RUN npm install

COPY . .
RUN npm run build --prod

# Stage 2
FROM nginx:alpine
COPY --from=build /app/dist/app-sec/ /usr/share/nginx/html/
EXPOSE 80:80
