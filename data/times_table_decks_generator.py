decks = []
deck = {"name": "Times Tables", "cards": []}
decks.append(deck)

for i in range(11):
    for j in range(11):
        card = {"question": f"What is {i} times {j}?",
                "answer": f"{i} * {j}", "results": []}
        decks[0]["cards"].append(card)

print(decks)
