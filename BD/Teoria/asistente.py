import sys
import ollama
import re
import os

def consultar_ia_local(peticion):
    prompt_sistema = (
        "Eres un experto en MongoDB. Tu única tarea es traducir el texto del usuario "
        "a una consulta válida de MongoDB (MQL). Devuelve ÚNICAMENTE el código JavaScript "
        "ejecutable para mongosh empezando por 'db.'. No incluyas explicaciones ni markdown."
    )

    try:
        response = ollama.generate(
            model='qwen2.5-coder:1.5b', # Has seleccionado la versión de 1.5b correctamente
            prompt=f"{prompt_sistema}\n\nUsuario: {peticion}",
            options={"temperature": 0.0} 
        )
        
        resultado = response['response'].strip()
        resultado = re.sub(r'```[a-zA-Z]*', '', resultado).replace('```', '').strip()
        
        ruta_txt = os.path.expanduser("~/.mongo_ia_res.txt")
        with open(ruta_txt, "w", encoding="utf-8") as f:
            # Ponemos una primera línea vacía para que el 'slice(1)' de Compass la descarte
            # y lea tu comando completo sin romperlo
            f.write(f"\n{resultado}")
            
        return resultado
    except Exception as e:
        ruta_txt = os.path.expanduser("~/.mongo_ia_res.txt")
        with open(ruta_txt, "w", encoding="utf-8") as f:
            f.write(f"\nError: {str(e)}")
        return f"Error: {str(e)}"

if __name__ == "__main__":
    if len(sys.argv) > 1:
        peticion_usuario = " ".join(sys.argv[1:])
        consultar_ia_local(peticion_usuario)
