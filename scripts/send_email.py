import os
import smtplib
import logging
from email.mime.text import MIMEText

# Configuração do logging
logging.basicConfig(level=logging.INFO)

def send_email(recipient, subject, body):
    sender_email = os.environ['EMAIL']
    password = os.environ['PASSWORD']

    msg = MIMEText(body)
    msg['Subject'] = subject
    msg['From'] = sender_email
    msg['To'] = recipient

    try:
        with smtplib.SMTP('smtp.gmail.com', 587) as server:
            server.starttls()
            server.login(sender_email, password)
            server.send_message(msg)
            logging.info(f'E-mail enviado para {recipient}')
    except Exception as e:
        logging.error(f'Erro ao enviar e-mail: {e}')

if __name__ == '__main__':
    recipient_email = os.environ['RECIPIENT']
    subject = "Notificação de Pipeline"
    body = "Pipeline executado!"
    send_email(recipient_email, subject, body)
