package main

import (
	"bytes"
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
	"regexp"
)

type info struct {
	id         string
	lang       string
	name       string
	url        string
	difficulty string
	file       string
}

var args = [5]int{1, 2, 1, 1, 1}
var regexs = [5]string{
	`(?m) \* @lc app=leetcode\.cn id=(\d*) lang=([a-z]*)`,
	`(?m) \* @lc app=leetcode\.cn id=(\d*) lang=([a-z]*)`,
	`(?m) \* \[[0-9]*\] ([\S ]*)`,
	`(?m) \* (http.*/)`,
	`(?m) \* ([A-Za-z]*) \(\d*\.?\d*%\)`,
}
var markdown = `# LeetCode解题记录

|序号|题目|难度|语言|解答|
| - | - | - | - | - |
@@table@@
`

var rootPath string
var fileRootPath string
var githubRootLink = "https://github.com/zqrren/leetcode"

func main() {
	rootPath, _ = filepath.Abs("./")
	files, dir := readFiles()
	b := new(bytes.Buffer)
	for _, file := range files {
		if file.IsDir() {
			continue
		}
		path := filepath.Join(dir.Name(), file.Name())
		infos := readInfo(args, regexs, path)
		b.WriteString(fmt.Sprintf("|%s|[%s](%s)|%s|%s|[%s](%s)|\n", infos.id, infos.name, infos.url, infos.difficulty, infos.lang, "link", infos.file))
	}
	markdown = regexp.MustCompile("(?m)@@table@@").ReplaceAllString(markdown, b.String())
	ioutil.WriteFile(filepath.Join(rootPath, "Readme.md"), []byte(markdown), 0644)
}

func readInfo(args [5]int, regexs [5]string, filePath string) (infos info) {
	log.Printf("reading【%s】...\n", filepath.Base(filePath))
	fileRootPath = filePath
	data, _ := ioutil.ReadFile(filePath)
	str := string(data)
	i := 0
	infos.id = reg(args, regexs, str, &i)
	infos.lang = reg(args, regexs, str, &i)
	infos.name = reg(args, regexs, str, &i)
	infos.url = reg(args, regexs, str, &i)
	infos.difficulty = reg(args, regexs, str, &i)
	infos.file = getFilePath()
	return
}

func readFiles() ([]os.FileInfo, *os.File) {
	dir, _ := os.Open(filepath.Join(rootPath, "problems"))
	defer dir.Close()
	files, _ := dir.Readdir(0)
	return files, dir
}

func getFilePath() (path string) {
	path = fileRootPath
	if len(os.Args) > 1 {
		path = githubRootLink + "/tree/main/problems/" + filepath.Base(path)
	}
	return
}

func reg(args [5]int, regex [5]string, data string, i *int) (res string) {
	re := regexp.MustCompile(regex[*i])
	match := re.FindStringSubmatch(data)
	if len(match) > args[*i] {
		res = match[args[*i]]
	} else {
		log.Printf("%s can't be match\n", regex[*i])
	}
	*i++
	return
}
