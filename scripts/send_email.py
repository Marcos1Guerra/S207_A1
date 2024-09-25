import os
import subprocess
import sys

# Função para instalar pacotes
def install(package):
    subprocess.check_call([sys.executable, '-m', 'pip', 'install', package])

# Instalar SendGrid se não estiver já instalado
try:
    import sendgrid
except ImportError:
    install('sendgrid')

import logging
from sendgrid import SendGridAPIClient
from sendgrid.helpers.mail import Mail

# Configuração do logging
logging.basicConfig(level=logging.INFO)

def send_email(recipient, subject, body):
    sender_email = os.environ['EMAIL']  # O remetente deve ser um e-mail verificado no SendGrid
    try:
        sg = SendGridAPIClient(os.environ['SENDGRID_API_KEY'])
        message = Mail(
            from_email=sender_email,
            to_emails=recipient,
            subject=subject,
            plain_text_content=body
        )
        response = sg.send(message)
        logging.info(f'E-mail enviado para {recipient}, Status Code: {response.status_code}')
    except Exception as e:
        logging.error(f'Erro ao enviar e-mail: {e}')

if __name__ == '__main__':
    recipient_email = os.environ['RECIPIENT']  # O e-mail do destinatário
    subject = "Notificação de Pipeline"
    body = "Pipeline executado!"
    send_email(recipient_email, subject, body)
