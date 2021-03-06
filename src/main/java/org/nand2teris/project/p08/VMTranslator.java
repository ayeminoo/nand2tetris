package org.nand2teris.project.p08;

import java.io.IOException;

public class VMTranslator {

    public static void main(String[] args) throws IOException {
        //read file
        //pare file
        //loop through and generate code

        Parser parser = new StackMachineParser(args[0]);
        CodeWriter writer = new HackAssemblyWriter(args[1]);

        while (parser.hasMoreCommands()) {
            parser.next();
            if (parser.commandType() == CommandType.ARITHMETIC_OR_LOGICAL) {
                writer.writeArithmeticCommand(parser.instruction().command);
            } else if (parser.commandType() == CommandType.MEMORY_ACCESS) {
                writer.writeMemoryAccessCommand(parser.instruction().command, parser.instruction().args[0], Integer.parseInt(parser.instruction().args[1]));
            }else if(parser.commandType() == CommandType.BRANCHING){
                writer.writeBranchingCommand(parser.instruction().command, parser.instruction().args[0]);
            }else if(parser.commandType() == CommandType.FUNCTION){
                writer.writeFunctionCommand();
            }
        }

        writer.close();
    }
}

