FROM node:17

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install -g http-server

COPY . .

EXPOSE 8089

CMD ["npm", "run", "dev"]