import json

import pulsar

client = pulsar.Client('pulsar://localhost:6650')
member_producer = client.create_producer('member-topic')
log_producer = client.create_producer('log-topic')

for i in range(10):
    member_producer.send(json.dumps({'id': i}).encode('utf-8'))
    log_producer.send(json.dumps({'id': i}).encode('utf-8'))

client.close()
