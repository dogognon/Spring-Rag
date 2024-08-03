import { Button, TextField } from "@vaadin/react-components";
import { ChatAiService } from "Frontend/generated/endpoints";
import { useState } from "react";
import Markdown from 'react-markdown';

export default function Chat(){
    const [question, setQuestion] = useState<string>("");
    const [response, setResponse] = useState<string | undefined>("");

    async function send() {
        ChatAiService.ragChat(question).then(response => setResponse(response));
    }

    return( 
    <div className="p-m">
    <h3>Chat Bot</h3>
    <div>
    <TextField style={{width: '80%' }} onChange={(e=>setQuestion (e.target.value))}/>
    <Button theme="primary" onClick={send}>Send</Button>
    <div>
        <Markdown>
            {response}
        </Markdown>
        </div>
    </div>
    </div>
    );
    }