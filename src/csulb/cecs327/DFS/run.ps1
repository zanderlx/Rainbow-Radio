param(
    [string]$numOfPeers = $(throw "Enter number of peers."),
    [string]$portNumber = $(throw "Enter port number.")
)
invoke-expression "cmd /c start powershell -NoExit -Command {java -cp ""E:\Users\lxzan\Desktop\CECS-327\Rainbow-Radio\lib\gson-2.8.5.jar;E:\Users\lxzan\Desktop\CECS-327\Rainbow-Radio\out\production\CECS-327-Music-Player"" csulb.cecs327.DFS.DFSCommand $portNumber}"
Start-Sleep -seconds 1
for($i = 1; $i -lt $numOfPeers; $i++){
    [int]$port = [convert]::ToInt32($portNumber, 10) + $i
    invoke-expression "cmd /c start powershell -NoExit -Command{java -cp ""E:\Users\lxzan\Desktop\CECS-327\Rainbow-Radio\lib\gson-2.8.5.jar;E:\Users\lxzan\Desktop\CECS-327\Rainbow-Radio\out\production\CECS-327-Music-Player"" csulb.cecs327.DFS.DFSCommand $port $portNumber}"
}