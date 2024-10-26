import json

import pulsar

client = pulsar.Client('pulsar://localhost:6650')
producer = client.create_producer('my-topic')

for i in range(10):
    producer.send(json.dumps({'id': i}).encode('utf-8'))

client.close()
