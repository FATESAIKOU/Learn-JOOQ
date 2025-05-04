# Use GraalVM as the base image
FROM ghcr.io/graalvm/native-image:ol8-java21-22.3.1 as builder

# Set working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the native image
RUN ./gradlew build -x test && \
    ./gradlew nativeCompile

# Use a minimal base image for the final executable
FROM gcr.io/distroless/base-debian11

# Set working directory
WORKDIR /app

# Copy the native executable from the builder stage
COPY --from=builder /app/build/native/nativeCompile/jooqtest .

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["/app/jooqtest"]