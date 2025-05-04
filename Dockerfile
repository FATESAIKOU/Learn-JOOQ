FROM ghcr.io/graalvm/native-image-community:21 AS builder

# Install necessary tools, including xargs
RUN microdnf install -y findutils zlib && \
    microdnf clean all

WORKDIR /app

COPY . .

# Build the native image
RUN ./gradlew build -x test && \
    ./gradlew nativeCompile

# Use the same GraalVM image for runtime
FROM ghcr.io/graalvm/native-image-community:21

WORKDIR /app

# Copy the native executable from the builder stage
COPY --from=builder /app/build/native/nativeCompile/jooqtest .

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["/app/jooqtest"]
