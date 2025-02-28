import SwiftUI

struct ContentView: View {
    @State private var tasks: [String] = []
    @State private var newTask = ""

    var body: some View {
        VStack {
            TextField("Enter a task", text: $newTask)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding()

            Button("Add Task") {
                if !newTask.isEmpty {
                    tasks.append(newTask)
                    newTask = ""
                }
            }
            .padding()

            List {
                ForEach(tasks, id: \.self) { task in
                    Text(task)
                }
                .onDelete { indexSet in
                    tasks.remove(atOffsets: indexSet)
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
