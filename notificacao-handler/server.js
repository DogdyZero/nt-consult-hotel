import amqp from "amqplib";
import 'dotenv/config'

const queueResponse = "notificacao-response-queue";
const exchangeResponse = "notificacao-response-exchange"
const routingResponse = "notificacao-response-routing-key"
const queueRequest = "notificacao-request-queue";
const exchangeRequest = "notificacao-request-exchange"
const routingRequest = "notificacao-request-routing-key";

const rabbitHost = process.env.RABBIT_MQ_HOST || localhost
console.log("Iniciado aplicação node.")

async function start() {

    let connection;
    let channel;
    try {
        connection = await amqp.connect(`amqp://${rabbitHost}`);
        channel = await connection.createChannel();
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
        if (channel)
            await channel.close();
    }
}

start()