# ════════════════════════════════════════════════════════
#  Copy AI Animal Images → Android drawable folder
# ════════════════════════════════════════════════════════

$srcDir = "C:\Users\SHASHANK\.gemini\antigravity\brain\5d31829b-1752-4b34-8a92-3be52d1c1bfe"
$dstDir = "C:\Users\SHASHANK\Downloads\Documents\project\MindMatrixpro\app\src\main\res\drawable"

$animals = @(
    "animal_tiger",
    "animal_elephant",
    "animal_leopard",
    "animal_bear",
    "animal_gaur",
    "animal_boar",
    "animal_crocodile",
    "animal_snake",
    "animal_wolf",
    "animal_deer",
    "animal_peacock",
    "animal_monkey"
)

Write-Host ""
Write-Host "Source : $srcDir"
Write-Host "Dest   : $dstDir"
Write-Host "────────────────────────────────────────"

$ok = 0; $fail = 0

foreach ($name in $animals) {
    $dst = Join-Path $dstDir "$name.png"
    $found = Get-ChildItem -Path $srcDir -Filter "$name*.png" -ErrorAction SilentlyContinue |
             Sort-Object LastWriteTime -Descending |
             Select-Object -First 1

    if ($found) {
        Copy-Item -Path $found.FullName -Destination $dst -Force
        $size = [math]::Round((Get-Item $dst).Length / 1KB, 1)
        Write-Host "  ✅  $name.png   ($size KB)"
        $ok++
    } else {
        Write-Host "  ❌  $name  — NOT FOUND in source folder"
        $fail++
    }
}

Write-Host "────────────────────────────────────────"
Write-Host "  Done:  $ok copied   |   $fail missing"
Write-Host ""
if ($fail -eq 0) {
    Write-Host "  🎉 All images ready! Rebuild the app in Android Studio."
} else {
    Write-Host "  ⚠  Some images missing. Check the source folder path above."
}
Write-Host ""
