import os
import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart


def send_email():
    # Obtendo variáveis de ambiente
    email = os.environ['EMAIL']
    password = os.environ['PASSWORD']
    recipient = os.environ['RECIPIENT']

    # Criando a mensagem
    subject = "Notificação do Pipeline"
    body = "Pipeline executado!"

    msg = MIMEMultipart()
    msg['From'] = email
    msg['To'] = recipient
    msg['Subject'] = subject

    msg.attach(MIMEText(body, 'plain'))

    try:
        # Conectando ao servidor SMTP
        with smtplib.SMTP('smtp.gmail.com', 587) as server:  # Altere conforme necessário
            server.starttls()
            server.login(email, password)
            server.send_message(msg)
            print("E-mail enviado com sucesso!")
    except Exception as e:
        print(f"Erro ao enviar e-mail: {e}")


if __name__ == "__main__":
    send_email()
