import os
import smtplib
from email.mime.text import MIMEText

# Recupera o destinatário da variável de ambiente
email_recipient = os.getenv('EMAIL_RECIPIENT')

# Verifica se o destinatário foi definido corretamente
if not email_recipient:
    raise ValueError("O endereço de e-mail não foi configurado corretamente.")

# Configuração do e-mail
msg = MIMEText("Pipeline executado com sucesso!")
msg['Subject'] = 'Notificação de Execução do Pipeline'
msg['From'] = 'seuemail@example.com'  # Substitua pelo remetente correto
msg['To'] = email_recipient

# Envia o e-mail
try:
    with smtplib.SMTP('smtp.exemplo.com', 587) as server:
        server.starttls()
        server.login("seuemail@example.com", "sua_senha")
        server.sendmail(msg['From'], [msg['To']], msg.as_string())
    print("E-mail enviado com sucesso!")
except Exception as e:
    print(f"Erro ao enviar o e-mail: {e}")
