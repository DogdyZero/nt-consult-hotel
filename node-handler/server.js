import amqp from "amqplib";
import 'dotenv/config'

const queueResponse = "notificacao-response-queue";
const exchangeResponse = "notificacao-response-exchange"
const routingResponse = "notificacao-response-routing-key"
const queueRequest = "notificacao-request-queue";
const exchangeRequest = "notificacao-request-exchange"
const routingRequest = "notificacao-request-routing-key";
const RETRY_INTERVAL = 5000; // 5 segundos


const amqpUrl = process.env.AMQP_URL || 'amqp://localhost:5673';
console.log("Iniciado aplicação node.")

async function connect() {
    let connection;
    while (!connection) {
        try {
            connection = await amqp.connect(amqpUrl);
        } catch (err) {
            console.log('RabbitMQ não está disponível, tentando novamente em 5 segundos...');
            await new Promise(resolve => setTimeout(resolve, RETRY_INTERVAL));
        }
    }
    return connection;
}
async function start() {

    try {
        const connection = await connect();
        if(connection) console.log("Sucessfully connect")
        const channel = await connection.createChannel();
        await channel.assertExchange(exchangeRequest, 'direct', { durable: true });
        await channel.assertQueue(queueRequest, { durable: true });
        await channel.bindQueue(queueRequest, exchangeRequest, routingRequest);

        await channel.consume(queueRequest, (msg) => {
            try {
                const mensagem = msg.content.toString()
                const reserva = JSON.parse(mensagem);
                console.log(`Prezado cliente ${reserva.nomeCliente}, o hotel ${reserva.nomeHotel} foi reservado para o checkin dia ${reserva.checkin} e checkout ${reserva.checkout}`)
                const resposta = { notificacaoEnviada: true, codigoReserva: reserva.codigoReserva, status: 'CONFIRMADO' }
                channel.publish(exchangeResponse, routingResponse, Buffer.from(JSON.stringify(resposta)));
            } catch (error) {
                console.error(error)
            }

        }, { noAck: true })

    } catch (err) {
        console.warn("Erro ao executar. ", err);
    }
}

start()
