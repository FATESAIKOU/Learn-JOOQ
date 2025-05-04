# Use GraalVM for building the native image
FROM ghcr.io/graalvm/native-image-community:21 AS builder

WORKDIR /app

# Copy project files
COPY . .

# Build the native image
RUN ./gradlew clean nativeCompile

FROM ghcr.io/graalvm/native-image-community:21

WORKDIR /app

# Copy the native executable from the builder stage
COPY --from=builder /app/build/native/nativeCompile/jooqtest .

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["/app/jooqtest"]
