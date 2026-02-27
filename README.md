# Custom Circuit Breaker Microservices (Java + Spring Boot)

Custom **Circuit Breaker** implementation for microservices to prevent cascading failures with **thread-safe state**, **recovery logic**, and **configurable thresholds**.

> Services: `api-gateway`, `discovery-service`, `order-service`, `inventory-service`, `payment-service`

---

## 🚀 What this project does

When a dependent service becomes **slow** or **unresponsive**, repeated calls can overload the system and cause a chain reaction of failures.

This project adds a **Circuit Breaker layer** to outgoing service calls to:

- Track failures over time
- Switch between **CLOSED → OPEN → HALF-OPEN**
- Enforce request **timeouts**
- Optionally return **fallback responses**

---

## 🧠 Circuit Breaker States

- **CLOSED**: Normal traffic. Failures are tracked.
- **OPEN**: Requests are blocked (fast-fail). No calls to the failing service.
- **HALF-OPEN**: Limited test calls allowed to check recovery.

---

## ✅ Must-Have Features (Implemented)

- **Failure Tracking**  
  Tracks failures over a window and calculates failure rate.

- **State Management**  
  CLOSED / OPEN / HALF-OPEN states with proper transitions.

- **Timeout Handling**  
  Requests exceeding timeout count as failures.

- **Concurrency Safety**  
  Shared state uses thread-safe structures (e.g., atomic counters / synchronized transitions).

- **Configurable Thresholds**  
  Thresholds and durations are configurable via `application.yml`.

---

## ⭐ Bonus Features (Optional / Extensible)

- Fallback handlers (default responses)
- Monitoring endpoint for circuit state (e.g., `/circuit/status`)
- Per-route circuit breakers (different configs for Inventory vs Payment etc.)
- Dashboard using actuator + metrics

---

## 🏗 Architecture

Client  
↓  
API Gateway (`api-gateway`)  
↓  
Service calls (Order → Payment/Inventory)  
↓  
**Custom Circuit Breaker** (outgoing call middleware/wrapper)  
↓  
Dependent Service

---

## 📦 Services

### `discovery-service`
Service discovery (Eureka) so services can find each other.

### `api-gateway`
Single entry point for clients. Routes requests to services.

### `order-service`
Orchestrates ordering flow. Calls `inventory-service` and/or `payment-service` through Circuit Breaker.

### `inventory-service`
Handles stock checks / inventory operations.

### `payment-service`
Handles payment operations (and is a typical “unstable dependency” in testing).

---

## ⚙️ Configuration (example)

Example config structure (your keys may differ — keep the same idea):

```yaml
circuitbreaker:
  failure-threshold: 50         # % failure rate to open circuit
  minimum-calls: 10             # minimum calls before evaluating failure rate
  wait-duration-open-state: 10s # how long circuit stays OPEN before HALF-OPEN
  permitted-calls-in-half-open: 3
  timeout-duration: 3s          # request timeout
  window-size: 20               # sliding window size (optional)
