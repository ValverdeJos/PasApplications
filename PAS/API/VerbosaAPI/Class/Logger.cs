using System;
using System.IO;

namespace APIEntraiment.Class
{
    public class Logger
    {
        private static readonly string logFilePath = @"C:\Users\josel\OneDrive\Bureau\Logs\log.txt";

        public static void Log(string message)
        {
            // Escreve a mensagem no arquivo de log
            using (StreamWriter writer = File.AppendText(logFilePath))
            {
                writer.WriteLine($"{DateTime.Now}: {message}");
            }
        }
    }
}
