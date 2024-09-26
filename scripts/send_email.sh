if [ -z "$RECIPIENT" ]; then
  echo "Erro: A variável de ambiente RECIPIENT não está definida."
  exit 1
fi

# Mensagem a ser enviada
MESSAGE="Pipeline executado!"
SUBJECT="Notificação do Pipeline"

# Envia o e-mail
echo "$MESSAGE" | mail -s "$SUBJECT" "$RECIPIENT"

# Verifica se o envio foi bem-sucedido
if [ $? -eq 0 ]; then
  echo "E-mail enviado com sucesso!"
else
  echo "Falha ao enviar o e-mail."
fi
