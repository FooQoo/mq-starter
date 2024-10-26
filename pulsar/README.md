# Local Pulsar Test

## Run Pulsar with Docker

```bash
docker run -it \
-p 6650:6650 \
-p 8080:8080 \
--mount source=pulsardata,target=/pulsar/data \
--mount source=pulsarconf,target=/pulsar/conf \
apachepulsar/pulsar:4.0.0 \
bin/pulsar standalone
```

Tips
- At least 128GB of disk space is recommended for the docker volume.

## Install dependencies

```bash
poetry install
```

### Consume messages

```bash
poetry run python consume.py
```

### Produce messages

```bash
poetry run python produce.py
```