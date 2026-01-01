import Editor from "@monaco-editor/react";

export default function CodeEditor({ language = "python", value, onChange }) {
    return (
        <Editor
            height="100%"
            language={language}
            theme="vs-dark"
            value={value}
            onChange={onChange}
            options={{
                minimap: { enabled: false },
                fontSize: 14,
                scrollBeyondLastLine: false,
                automaticLayout: true,
                tabSize: 2,
            }}
        />
    );
}