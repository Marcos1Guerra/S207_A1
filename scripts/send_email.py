import os
import smtplib
from email.mime.text import MIMEText

def send_email():
    recipient = os.getenv('EMAIL')
    if not recipient:
        raise ValueError("O endereço de e-mail do destinatário não foi definido.")

    # Configurar o corpo do e-mail
    msg = MIMEText("Pipeline executado com sucesso!")
    msg['Subject'] = 'Notificação do Pipeline'
    msg['From'] = 'remetente@dominio.com'  # Remetente genérico
    msg['To'] = recipient

    # Conectar ao servidor SMTP
    try:
        server = smtplib.SMTP('smtp.exemplo.com', 587)  # Substitua pelo seu servidor SMTP
        server.starttls()
        server.login('seu-email@dominio.com', 'sua-senha')  # Substitua pelo seu e-mail e senha
        server.sendmail(msg['From'], [msg['To']], msg.as_string())
        server.quit()
        print("E-mail enviado com sucesso!")
    except Exception as e:
        print(f"Erro ao enviar e-mail: {e}")
        exit(1)

if __name__ == "__main__":
    send_email()
