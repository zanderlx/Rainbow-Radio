param(
    [string]$numOfPeers = $(throw "Enter number of peers."),
    [string]$portNumber = $(throw "Enter port number.")
)
invoke-expression "cmd /c start powershell -NoExit -Command {java -cp ""C:\Users\Sinat\IdeaProjects\Rainbow-Radio1234\lib\gson-2.8.5.jar;C:\Users\Sinat\IdeaProjects\Rainbow-Radio123456\out\production\CECS-327-Music-Player"" csulb.cecs327.DFS.DFSCommand $portNumber}"
Start-Sleep -seconds 1
for($i = 1; $i -lt $numOfPeers; $i++){
    [int]$port = [convert]::ToInt32($portNumber, 10) + $i
    invoke-expression "cmd /c start powershell -NoExit -Command{java -cp ""C:\Users\Sinat\IdeaProjects\Rainbow-Radio1234\lib\gson-2.8.5.jar;C:\Users\Sinat\IdeaProjects\Rainbow-Radio123456\out\production\CECS-327-Music-Player"" csulb.cecs327.DFS.DFSCommand $port $portNumber}"
}