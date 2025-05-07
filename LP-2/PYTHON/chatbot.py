import re

responses = {
    r"\b(hey|hi|hello)\b" : "Hey there ! how can i help you?"
}

def chatbot(user):
    user = user.lower()
    for pattern, response in responses.items():
        if re.search(pattern, user) :
            return response
    return "I'm sorry, I don't understand you. Please try again."

print("Welcome to GROCERY Chatbot")

while True:
    user_msg = input("You:")
    if user_msg.lower() == ["bye"]:
        print("Chatbot: Goodbye")
        break
    response = chatbot(user_msg)
    print("Chatbot:", response)