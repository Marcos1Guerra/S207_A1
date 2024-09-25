import os
import smtplib
from email.mime.text import MIMEText

def send_email(recipient, subject, body):
    sender_email = os.environ['EMAIL']
    password = os.environ['EMAIL_PASSWORD']

    msg = MIMEText(body)
    msg['Subject'] = subject
    msg['From'] = sender_email
    msg['To'] = recipient

    try:
        with smtplib.SMTP('smtp.gmail.com', 587) as server:
            server.starttls()
            server.login(sender_email, password)
            server.send_message(msg)
            print(f'E-mail enviado para {recipient}')
    except Exception as e:
        print(f'Erro ao enviar e-mail: {e}')

if __name__ == '__main__':
    recipient_email = os.environ['RECIPIENT']
    subject = "Notificação de Pipeline"
    body = "Pipeline executado!"
    send_email(recipient_email, subject, body)
