
FROM gradle:7.5.0-jdk11
WORKDIR /app
COPY . .
CMD ["gradle", "test"]
