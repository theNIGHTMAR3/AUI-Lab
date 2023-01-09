FROM nginx:1.23-alpine

WORKDIR /etc/nginx/conf.d

COPY ./nginx.conf /etc/nginx/conf.d/

RUN mv nginx.conf default.conf

CMD ["nginx", "-g", "daemon off;"]