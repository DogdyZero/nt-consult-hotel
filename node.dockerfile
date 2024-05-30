FROM node:18-alpine
WORKDIR /app

COPY node-handler/package.json /app/
COPY node-handler/server.js /app/

RUN npm i

EXPOSE 3000

CMD ["npm", "run", "dev"]
